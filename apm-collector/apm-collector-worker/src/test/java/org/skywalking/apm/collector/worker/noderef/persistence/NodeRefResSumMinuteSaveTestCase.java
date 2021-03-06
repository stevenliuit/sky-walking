package org.skywalking.apm.collector.worker.noderef.persistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skywalking.apm.collector.actor.ClusterWorkerContext;
import org.skywalking.apm.collector.actor.LocalWorkerContext;
import org.skywalking.apm.collector.actor.selector.HashCodeSelector;
import org.skywalking.apm.collector.worker.noderef.NodeRefResSumIndex;

import java.util.TimeZone;

/**
 * @author pengys5
 */
public class NodeRefResSumMinuteSaveTestCase {

    private NodeRefResSumMinuteSave save;

    @Before
    public void init() {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        ClusterWorkerContext cluster = new ClusterWorkerContext(null);
        LocalWorkerContext local = new LocalWorkerContext();
        save = new NodeRefResSumMinuteSave(NodeRefResSumMinuteSave.Role.INSTANCE, cluster, local);
    }

    @Test
    public void testEsIndex() {
        Assert.assertEquals(NodeRefResSumIndex.INDEX, save.esIndex());
    }

    @Test
    public void testEsType() {
        Assert.assertEquals(NodeRefResSumIndex.TYPE_MINUTE, save.esType());
    }

    @Test
    public void testRole() {
        Assert.assertEquals(NodeRefResSumMinuteSave.class.getSimpleName(), NodeRefResSumMinuteSave.Role.INSTANCE.roleName());
        Assert.assertEquals(HashCodeSelector.class.getSimpleName(), NodeRefResSumMinuteSave.Role.INSTANCE.workerSelector().getClass().getSimpleName());
    }

    @Test
    public void testFactory() {
        NodeRefResSumMinuteSave.Factory factory = new NodeRefResSumMinuteSave.Factory();
        Assert.assertEquals(NodeRefResSumMinuteSave.class.getSimpleName(), factory.role().roleName());
        Assert.assertEquals(NodeRefResSumMinuteSave.class.getSimpleName(), factory.workerInstance(null).getClass().getSimpleName());
    }
}
