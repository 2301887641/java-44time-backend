package com.time.article.service.converter.business.user;

import com.time.article.dao.entity.business.User;
import com.time.article.service.dto.business.user.UserDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-11T09:47:22+0800",
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
        userDto.setName( arg0.getName() );

        return userDto;
    }

    @Override
    public User dtoToEntity(UserDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        User user = new User();

        user.setId( arg0.getId() );
        user.setName( arg0.getName() );

        return user;
    }
}
