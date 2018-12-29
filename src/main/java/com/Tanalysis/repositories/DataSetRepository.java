package com.Tanalysis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.Tanalysis.entities.DataSet;

@RepositoryRestResource
public interface DataSetRepository extends JpaRepository<DataSet, Long>{

}
