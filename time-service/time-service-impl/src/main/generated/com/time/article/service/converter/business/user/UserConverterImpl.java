package com.time.article.service.converter.business.user;

import com.time.article.dao.criteria.business.user.UserCriteria;
import com.time.article.dao.entity.business.user.User;
import com.time.article.service.criteria.business.user.UserCriteriaDto;
import com.time.article.service.dto.business.user.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-15T19:44:10+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_51 (Oracle Corporation)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDto entityToDto(User arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( arg0.getId() );
        userDto.setMobile( arg0.getMobile() );
        userDto.setUsername( arg0.getUsername() );
        userDto.setEmail( arg0.getEmail() );
        userDto.setPassword( arg0.getPassword() );
        userDto.setSalt( arg0.getSalt() );
        userDto.setAvatar( arg0.getAvatar() );
        userDto.setAdministrator( arg0.getAdministrator() );
        userDto.setStatus( arg0.getStatus() );
        userDto.setRegisterIp( arg0.getRegisterIp() );

        return userDto;
    }

    @Override
    public List<UserDto> entityToDto(List<User> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( arg0.size() );
        for ( User user : arg0 ) {
            list.add( entityToDto( user ) );
        }

        return list;
    }

    @Override
    public User dtoToEntity(UserDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        User user = new User();

        user.setId( arg0.getId() );
        user.setMobile( arg0.getMobile() );
        user.setUsername( arg0.getUsername() );
        user.setEmail( arg0.getEmail() );
        user.setPassword( arg0.getPassword() );
        user.setSalt( arg0.getSalt() );
        user.setAvatar( arg0.getAvatar() );
        user.setAdministrator( arg0.getAdministrator() );
        user.setStatus( arg0.getStatus() );
        user.setRegisterIp( arg0.getRegisterIp() );

        return user;
    }

    @Override
    public UserCriteria criteriaDTOToCriteriaEntity(UserCriteriaDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserCriteria userCriteria = new UserCriteria();

        userCriteria.setId( arg0.getId() );
        userCriteria.setMobile( arg0.getMobile() );
        userCriteria.setUsername( arg0.getUsername() );
        userCriteria.setEmail( arg0.getEmail() );
        userCriteria.setPassword( arg0.getPassword() );
        userCriteria.setSalt( arg0.getSalt() );
        userCriteria.setAvatar( arg0.getAvatar() );
        userCriteria.setAdministrator( arg0.getAdministrator() );
        userCriteria.setStatus( arg0.getStatus() );
        userCriteria.setRegisterIp( arg0.getRegisterIp() );

        return userCriteria;
    }
}
