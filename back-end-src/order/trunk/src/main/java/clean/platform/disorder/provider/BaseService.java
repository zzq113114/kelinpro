package clean.platform.disorder.provider;

import clean.platform.disorder.configuer.UIDGenerater;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

/**
 * 服务实现基类
 *
 * @author ZX
 * @create 2020-03-10 2:25
 **/

public class BaseService {

    @Autowired
    private UIDGenerater uidGenerater;

    public Long getId(){
        return uidGenerater.nextId();
    }

    public Date getNowDate(){
        return Calendar.getInstance().getTime();
    }
}
