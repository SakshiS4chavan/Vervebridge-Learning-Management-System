package dto;


import java.util.HashSet;
import java.util.Set;

import model.TypeEvaluation.TipEvaluacije;

public class TypeEvalutionDTO {

	private Long id;
	private TipEvaluacije tipEvaluacije;
	private Set<EvaluationKnowledgeDTO> evaluationKnowledge = new HashSet<EvaluationKnowledgeDTO>();

	public TypeEvalutionDTO() {
		super();
	}
	
	public TypeEvalutionDTO(Long id, TipEvaluacije tipEvaluacije) {
		super();
		this.id = id;
		this.tipEvaluacije = tipEvaluacije;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TipEvaluacije getTipEvaluacije() {
		return tipEvaluacije;
	}
	
	public void setTipEvaluacije(TipEvaluacije tipEvaluacije) {
		this.tipEvaluacije = tipEvaluacije;
	}

	public Set<EvaluationKnowledgeDTO> getEvaluationKnowledge() {
		return evaluationKnowledge;
	}

	public void setEvaluationKnowledge(Set<EvaluationKnowledgeDTO> evaluationKnowledge) {
		this.evaluationKnowledge = evaluationKnowledge;
	}
}
