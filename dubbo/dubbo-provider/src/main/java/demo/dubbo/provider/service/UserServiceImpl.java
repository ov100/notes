package demo.dubbo.provider.service;

import demo.dubbo.dto.User;
import demo.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author : gb 2019/4/18
 */
@Service(version = "${demo.service.version}")
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return User.builder()
                .id(1).name("test").address("beijing")
                .age(30).sex(1)
                .build();
    }
}
