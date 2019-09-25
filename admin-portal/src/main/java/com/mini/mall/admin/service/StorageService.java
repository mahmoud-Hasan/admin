package com.mini.mall.admin.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void store(MultipartFile file,List<MultipartFile> file2,String mainPath);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

	void deleteAll(String imageName) throws IOException ;

	void init();

}