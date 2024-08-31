package dto;

import java.time.LocalDateTime;

public class EducationGoalDTO {

	private Long id;
	private String opis;
	private TopicDTO topic;
	private LocalDateTime vremeKraja;
	private TypeOfTeachingDTO typeOfTeaching;
	private SubjectRealizationDTO subjectRealization;
	
	public EducationGoalDTO() {
		super();
	}
	
	public EducationGoalDTO(Long id, String opis, TopicDTO topic) {
		super();
		this.id = id;
		this.opis = opis;
		this.topic = topic;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOpis() {
		return opis;
	}
	
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public TopicDTO getTopic1() {
		return topic;
	}
	
	public void setTopic1(TopicDTO topic) {
		this.topic = topic;
	}

	public LocalDateTime getVremeKraja() {
		return getVremeKraja();
	}
	
	public void setVremeKraja(LocalDateTime vremeKraja) {
		this.vremeKraja = vremeKraja;
	}
	
	public TopicDTO getTopic() {
		return topic;
	}
	
	public void setTopic(TopicDTO topic) {
		this.topic = topic;
	}
	
	public TypeOfTeachingDTO getTypeOfTeaching() {
		return getTypeOfTeaching();
	}
	
	public void setTypeOfTeaching(TypeOfTeachingDTO typeOfTeaching) {
		this.typeOfTeaching = typeOfTeaching;
	}

	public SubjectRealizationDTO getSubjectRealization() {
		return getSubjectRealization();
	}

	public void setSubjectRealization(SubjectRealizationDTO subjectRealization) {
		this.subjectRealization = subjectRealization;
	}
}
