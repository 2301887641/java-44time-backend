package com.time.article.service.converter.business.permission;

import com.time.article.dao.criteria.business.permission.ResourceCriteria;
import com.time.article.dao.entity.business.permission.Resource;
import com.time.article.service.criteria.business.permission.ResourceCriteriaDto;
import com.time.article.service.dto.business.permission.ResourceDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-16T19:23:58+0800",
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
        resourceDto.setLft( arg0.getLft() );
        resourceDto.setRgt( arg0.getRgt() );
        resourceDto.setLevel( arg0.getLevel() );
        resourceDto.setName( arg0.getName() );
        resourceDto.setCode( arg0.getCode() );
        resourceDto.setDescription( arg0.getDescription() );
        resourceDto.setIcon( arg0.getIcon() );
        resourceDto.setResourceType( arg0.getResourceType() );
        resourceDto.setUrl( arg0.getUrl() );

        return resourceDto;
    }

    @Override
    public List<ResourceDto> entityToDto(List<Resource> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>( arg0.size() );
        for ( Resource resource : arg0 ) {
            list.add( entityToDto( resource ) );
        }

        return list;
    }

    @Override
    public Resource dtoToEntity(ResourceDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Resource resource = new Resource();

        resource.setId( arg0.getId() );
        resource.setLevel( arg0.getLevel() );
        resource.setLft( arg0.getLft() );
        resource.setRgt( arg0.getRgt() );
        resource.setName( arg0.getName() );
        resource.setCode( arg0.getCode() );
        resource.setDescription( arg0.getDescription() );
        resource.setIcon( arg0.getIcon() );
        resource.setResourceType( arg0.getResourceType() );
        resource.setUrl( arg0.getUrl() );

        return resource;
    }

    @Override
    public ResourceCriteria criteriaDtoToCriteriaEntity(ResourceCriteriaDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ResourceCriteria resourceCriteria = new ResourceCriteria();

        resourceCriteria.setId( arg0.getId() );
        resourceCriteria.setLevel( arg0.getLevel() );
        resourceCriteria.setLft( arg0.getLft() );
        resourceCriteria.setRgt( arg0.getRgt() );
        resourceCriteria.setName( arg0.getName() );
        resourceCriteria.setCode( arg0.getCode() );
        resourceCriteria.setDescription( arg0.getDescription() );
        resourceCriteria.setIcon( arg0.getIcon() );
        resourceCriteria.setResourceType( arg0.getResourceType() );
        resourceCriteria.setUrl( arg0.getUrl() );

        return resourceCriteria;
    }
}
