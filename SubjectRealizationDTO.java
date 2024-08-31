package dto;



import java.util.HashSet;
import java.util.Set;

import model.EvalutionKnowledge;

public class SubjectRealizationDTO {
	
	private Long id;
	private SubjectDTO subject;
	private TeacherOnRealizationDTO teacherOnRealization;
	private Set<EvalutionKnowledge> evaluationKnowledge = new HashSet<EvaluationKnowledge>();
	
	public SubjectRealizationDTO() {
		super();
	}
	
	public SubjectRealizationDTO(Long id, SubjectDTO subject) {
		super();
		this.id = id;
		this.subject = subject;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public SubjectDTO getSubject() {
		return subject;
	}
	
	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}
	
	public TeacherOnRealizationDTO getTeacherOnRealization() {
		return teacherOnRealization;
	}
	
	public void setTeacherOnRealization(TeacherOnRealizationDTO teacherOnRealization) {
		this.teacherOnRealization = teacherOnRealization;
	}

	public Set<EvalutionKnowledge> getEvaluationKnowledge() {
		return evaluationKnowledge;
	}

	public void setEvalutionKnowledge(Set<EvaluationKnowledge> evaluationKnowledge) {
		this.evaluationKnowledge = evaluationKnowledge;
	}
}
