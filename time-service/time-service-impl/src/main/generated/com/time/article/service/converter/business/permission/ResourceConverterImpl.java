package com.time.article.service.converter.business.permission;

import com.time.article.dao.entity.business.permission.Resource;
import com.time.article.service.dto.business.permission.ResourceDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-12T20:51:40+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_51 (Oracle Corporation)"
)
public class ResourceConverterImpl implements ResourceConverter {

    @Override
    public ResourceDto entityToDto(Resource arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ResourceDto resourceDto = new ResourceDto();

        resourceDto.setId( arg0.getId() );
        resourceDto.setName( arg0.getName() );
        resourceDto.setCode( arg0.getCode() );
        resourceDto.setDescription( arg0.getDescription() );
        resourceDto.setIcon( arg0.getIcon() );
        resourceDto.setResourceType( arg0.getResourceType() );
        resourceDto.setUrl( arg0.getUrl() );

        return resourceDto;
    }

    @Override
    public Resource dtoToEntity(ResourceDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Resource resource = new Resource();

        resource.setId( arg0.getId() );
        resource.setName( arg0.getName() );
        resource.setCode( arg0.getCode() );
        resource.setDescription( arg0.getDescription() );
        resource.setIcon( arg0.getIcon() );
        resource.setResourceType( arg0.getResourceType() );
        resource.setUrl( arg0.getUrl() );

        return resource;
    }
}
