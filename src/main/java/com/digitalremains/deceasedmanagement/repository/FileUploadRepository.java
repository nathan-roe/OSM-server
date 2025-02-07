package com.digitalremains.deceasedmanagement.repository;

import com.digitalremains.deceasedmanagement.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
}
