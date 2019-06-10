package aerospike;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.IAerospikeClient;
import static org.mockito.Mockito.*;

import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AerospikeDaoTest {

    AerospikeDao dao;
    static WritePolicy policy;
    static AerospikeConfiguration aerospikeConfiguration;

    @BeforeClass
    public static void setup() {
        aerospikeConfiguration = mock(AerospikeConfiguration.class);
        policy = new WritePolicy();

        doReturn("namespace").when(aerospikeConfiguration).getNamespace();
        doReturn("set").when(aerospikeConfiguration).getSetName();
    }

    @Test(expected = AerospikeException.class)
    public void testWriteLineAerospikeFailure() {
        IAerospikeClient aerospikeClient = mock(IAerospikeClient.class);
        doThrow(new AerospikeException()).when(aerospikeClient).put(any(WritePolicy.class), any(Key.class), any(Bin.class));

        dao = new AerospikeDao(aerospikeClient, policy, aerospikeConfiguration);
        dao.writeLine(0, "hello");
    }

    @Test(expected = AerospikeException.class)
    public void testGetLineAersopikeFailure() {
        IAerospikeClient aerospikeClient = mock(IAerospikeClient.class);
        Bin bin = new Bin("text", "hello");

        doThrow(new AerospikeException()).when(aerospikeClient).get(any(WritePolicy.class), any(Key.class));

        dao = new AerospikeDao(aerospikeClient, policy, aerospikeConfiguration);

        dao.getLine(1);
    }

    @Test
    public void testGetLineNormalCase() {
     IAerospikeClient aerospikeClient = mock(IAerospikeClient.class);
        Bin bin = new Bin("text", "hello");

        doReturn(
                new Record(ImmutableMap.of("text", "hello"), 1, 1)
        ).when(aerospikeClient).get(any(WritePolicy.class), any(Key.class));

        dao = new AerospikeDao(aerospikeClient, policy, aerospikeConfiguration);

        Assert.assertEquals(dao.getLine(1), "hello");
    }

    @Test
    public void testWriteLineNormalCase() {
     IAerospikeClient aerospikeClient = mock(IAerospikeClient.class);
        Bin bin = new Bin("text", "hello");
        dao = new AerospikeDao(aerospikeClient, policy, aerospikeConfiguration);
        doNothing().when(aerospikeClient).put(any(WritePolicy.class), any(Key.class), any(Bin.class));
        Assert.assertTrue(dao.writeLine(1, "hello"));
    }
}