package dto;



public class TypeOfTopicDTO<naziv> {
	
	private Long id;
	private TypeTopic naziv;

	public TypeOfTopicDTO() {
		super();
	}

	public TypeOfTopicDTO(Long id, TypeTopic naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public TypeOfTopicDTO(Object id2, Object naziv2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeTopic getNaziv() {
		return naziv;
	}

	public void setNaziv(TypeTopic naziv) {
		this.naziv = naziv;
	}
}
