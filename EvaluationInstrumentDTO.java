package dto;



import java.awt.FileDialog;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class EvaluationInstrumentDTO {
	
	private Long id;
	private FileDialog file;
	private String tipTestiranja;
	private Set<EvaluationKnowledgeDTO> evaluationKnowledge = new HashSet<EvaluationKnowledgeDTO>();
	private FileDialog file2;

	public EvaluationInstrumentDTO() {
		super();
	}
	
	public EvaluationInstrumentDTO(Long id, String tipTestiranja, FileDialog file) {
		super();
		this.id = id;
		this.tipTestiranja = tipTestiranja;
		file2 = file;
		this.file = file;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public FileDialog getFile() {
		return file;
	}

	public void setFile(FileDialog file) {
		this.file = file;
	}

	public String getTipTestiranja() {
		return tipTestiranja;
	}

	public void setTipTestiranja(String tipTestiranja) {
		this.tipTestiranja = tipTestiranja;
	}

	public Set<EvaluationKnowledgeDTO> getEvaluationKnowledge() {
		return evaluationKnowledge;
	}

	public void setEvaluationKnowledge(Set<EvaluationKnowledgeDTO> evaluationKnowledge) {
		this.evaluationKnowledge = evaluationKnowledge;
	}
}
