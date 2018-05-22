package container;


import io.vitess.dao.so.SoInvFlowDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class TestInv {

    @Autowired
    private SoInvFlowDao soInvFlowDao;

    @Test
    public void tet(){
        while (true){
            soInvFlowDao.updateSkuInv("NKA3416149-472-4", 1);
        }
    }
}
