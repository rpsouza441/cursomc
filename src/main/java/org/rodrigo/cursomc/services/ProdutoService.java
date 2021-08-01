package org.rodrigo.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.domain.Produto;
import org.rodrigo.cursomc.repositories.CategoriaRepository;
import org.rodrigo.cursomc.repositories.ProdutoRepository;
import org.rodrigo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private S3Service s3;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.prod}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;
	

	@Transactional
	public Produto insert(Produto obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}


	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest p = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> listaCategorias = categoriaRepo.findAllById(ids);
		return repo.search(nome, listaCategorias, p);

	}

	public void uploadPicture(MultipartFile mf, Integer id) {
		BufferedImage jpgImage = imageService.getJpgImageFromFile(mf);
		String fileName = prefix + id + ".jpg";
		jpgImage = imageService.cropSquare(jpgImage);
		s3.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
		jpgImage = imageService.resize(jpgImage, size);
		fileName = prefix + id + ".-small.jpg";
		s3.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

	}

}
