package io.vitess.c3p0;

import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.UnsignedLong;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.vitess.client.Context;
import io.vitess.client.RpcClient;
import io.vitess.client.VTGateBlockingConnection;
import io.vitess.client.VTSession;
import io.vitess.client.cursor.Cursor;
import io.vitess.client.cursor.Row;
import io.vitess.client.grpc.GrpcClientFactory;
import io.vitess.proto.Query;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.Test;

import java.util.Map;
import java.util.Random;

/**
 * @author YSH4807
 * @date 2018/3/26 16:05
 */
public class TestVitessClient {

    @Test
    public void testClient(){

        Context ctx = Context.getDefault().withDeadlineAfter(Duration.millis(5 * 1000));
//        try (RpcClient client = new GrpcClientFactory().create(ctx, args[0]);
        try (RpcClient client = new GrpcClientFactory().create(ctx, "10.88.27.50:15991");
             VTGateBlockingConnection conn = new VTGateBlockingConnection(client)) {
            VTSession session = new VTSession("@master", Query.ExecuteOptions.getDefaultInstance());
            // Insert some messages on random pages.
            System.out.println("Inserting into master...");
            Random rand = new Random();
            for (int i = 0; i < 3; i++) {
                Instant timeCreated = Instant.now();
                Map<String, Object> bindVars =
                        new ImmutableMap.Builder<String, Object>()
                                .put("page", rand.nextInt(100) + 1)
                                .put("time_created_ns", timeCreated.getMillis() * 1000000)
                                .put("message", "V is for speed")
                                .build();

                conn.execute(ctx, "begin", null, session);
                conn.execute(
                        ctx,
                        "INSERT INTO messages (page,time_created_ns,message) VALUES (:page,:time_created_ns,:message)",
                        bindVars, session);
                conn.execute(ctx, "commit", null, session);
            }

            // Read it back from the master.
            System.out.println("Reading from master...");
            try (Cursor cursor =
                         conn.execute(
                                 ctx,
                                 "SELECT page, time_created_ns, message FROM messages",
                                 null, session)) {
                Row row;
                while ((row = cursor.next()) != null) {
                    UnsignedLong page = row.getULong("page");
                    UnsignedLong timeCreated = row.getULong("time_created_ns");
                    byte[] message = row.getBytes("message");
                    System.out.format("(%s, %s, %s)\n", page, timeCreated, new String(message));
                }
            }
        } catch (Exception e) {
            System.out.println("Vitess Java example failed.");
            System.out.println("Error Details:");
            e.printStackTrace();
            System.exit(2);
        }
    }
}
