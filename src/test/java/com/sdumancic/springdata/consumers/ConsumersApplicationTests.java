package com.sdumancic.springdata.consumers;

import oracle.AQ.*;
import oracle.jms.AQjmsTopicConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumersApplicationTests {

    public class MMSMessage implements SQLData {
        private String sql_type = "MMSMessage";
       /* Long message_id;
        Long batch_id;
        Long cust_id;
        Long client_id;*/
        Long message;

        /*public Long getMessage_id() {
            return message_id;
        }

        public void setMessage_id(Long message_id) {
            this.message_id = message_id;
        }

        public Long getBatch_id() {
            return batch_id;
        }

        public void setBatch_id(Long batch_id) {
            this.batch_id = batch_id;
        }

        public Long getCust_id() {
            return cust_id;
        }

        public void setCust_id(Long cust_id) {
            this.cust_id = cust_id;
        }

        public Long getClient_id() {
            return client_id;
        }

        public void setClient_id(Long client_id) {
            this.client_id = client_id;
        }
*/
        public Long getMessage() {
            return message;
        }

        public void setMessage(Long message) {
            this.message = message;
        }

        @Override
        public String getSQLTypeName() throws SQLException {
            return sql_type;
        }

        @Override
        public void readSQL(SQLInput stream, String typeName) throws SQLException {
            this.sql_type=typeName;
           /* this.message_id = stream.readLong();
            this.batch_id = stream.readLong();
            this.cust_id = stream.readLong();
            this.client_id = stream.readLong();*/
            this.message = stream.readLong();

        }

        @Override
        public void writeSQL(SQLOutput stream) throws SQLException {
           /* stream.writeLong(this.message_id);
            stream.writeLong(this.batch_id);
            stream.writeLong(this.cust_id);
            stream.writeLong(this.client_id);*/
            stream.writeLong(this.message);
        }
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void consumeMessage() throws ClassNotFoundException, SQLException, AQException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@mars:61525:crispd12", "CRM_ADMIN", "CRM_ADMIN");
        connection.setAutoCommit(false);
        boolean  new_messages = true;

        Class.forName("oracle.AQ.AQOracleDriver");

        AQSession aqSession = AQDriverManager.createAQSession(connection);
        AQQueueTable q_table = aqSession.getQueueTable ("CRM_ADMIN", "messages_qt");
        System.out.println("Successful getQueueTable");

        /* Get a handle to a queue - aq_queue4 in aquser schema: */
        AQQueue  queue = aqSession.getQueue ("CRM_ADMIN", "mms_messages_queue");
        System.out.println("Successful getQueue");

        AQDequeueOption deq_option = new AQDequeueOption();


        AQMessage  message;
        AQObjectPayload obj_payload;
        while(new_messages) {
            try {
                message = queue.dequeue(deq_option, MMSMessage.class);
                obj_payload = message.getObjectPayload();

                MMSMessage deq_message = (MMSMessage) (obj_payload.getPayloadData());

                System.out.println(deq_message.getMessage());
            } catch (AQException aqex) {
                new_messages = false;
                System.out.println("No more messages titles");
                System.out.println("Exception-1: " + aqex);
            }
        }

    }
}
