package com.mini.mall.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mini.mall.admin.exception.StorageException;
import com.mini.mall.admin.exception.StorageFileNotFoundException;
import com.mini.mall.admin.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	private Path rootLocation;
	File s;

	@Autowired
	public StorageServiceImpl() {
		this.rootLocation = Paths.get("D:\\uploadingDir\\newDir");
		s = new File("D:\\uploadingDir\\newDir");
	}

	@Override
	public void store(MultipartFile file, List<MultipartFile> file2, String mainPath) {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + filename);
			}

			try (InputStream inputStream = file.getInputStream()) {

				mainDirUtil(filename, inputStream, file2, mainPath);

			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	// create method that take the main image name and split it to delete the file
	// type after(.) then make a directory with that name
	private void mainDirUtil(String mainImageName, InputStream inputStream, List<MultipartFile> file2,
			String mainPath) {
		String newDir = mainImageName.substring(0, mainImageName.indexOf("."));
		Path newMainPath = Paths.get(mainPath + "\\" + newDir);

		// renaming the files before storing them
		// -----------------------
//    	 Files.copy(inputStream, this.rootLocation.resolve("_1.jpg"),
//                 StandardCopyOption.REPLACE_EXISTING);
//    	 ------------------
		try {
			Files.createDirectories(newMainPath);
			Files.copy(inputStream, newMainPath.resolve(mainImageName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
		int renaming_index =2;
		for(int i=0;i<file2.size();i++) {
			System.out.println(renaming_index);
			try (InputStream inputStreame = file2.get(i).getInputStream()) {
				
				//return the folder path in all_url
				// replaced by renaming :: StringUtils.cleanPath(file2.get(i).getOriginalFilename())
				Files.copy(inputStreame, newMainPath.resolve("_"+renaming_index+".jpg"),
						StandardCopyOption.REPLACE_EXISTING);
				renaming_index++;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll(String imageName) throws IOException {
		// FileSystemUtils.deleteRecursively(rootLocation.toFile());
		FileSystemUtils.deleteRecursively(
				new File("D:\\uploadingDir\\newDir\\36682070_1036603866494478_6003432224660127744_n.jpg"));
		System.out.println(s.isFile() + "::" + s.isDirectory());
		System.out.println(s.getName());
//       System.out.println(s.listFiles().length+"::"+s.exists());
		for (File f : s.listFiles()) {
			if (f.isFile() && f.getName().equals(imageName))
				FileSystemUtils.deleteRecursively(f);
			System.out.println(f.getAbsolutePath());

		}
		// FileSystemUtils.deleteRecursively(s);
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
