package com.time.article.service.converter.business.user;

import com.time.article.dao.entity.business.Person;
import com.time.article.service.dto.business.user.PersonDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-03T09:15:31+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_51 (Oracle Corporation)"
)
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDto entityToDto(Person arg0) {
        if ( arg0 == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        return personDto;
    }

    @Override
    public Person dtoToEntity(PersonDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Person person = new Person();

        return person;
    }
}
