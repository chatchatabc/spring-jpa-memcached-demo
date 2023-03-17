package spring.jpa.memcached.demo.user.application.commons.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.jpa.memcached.demo.user.application.commons.vo.UserVO;
import spring.jpa.memcached.demo.user.domain.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserVO modelToVO(User user);

    User voToModel(UserVO userVO);

}
