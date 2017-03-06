package com.revature.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ie_eval_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EvalType implements Serializable {

	@Id
	@Column(name="et_id")
	private Integer id;
	
	@Column(name="et_description")
	private String description;
	
	public EvalType() {/*empty constructor needed*/}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 19 * hash + Objects.hashCode(this.id);
		hash = 19 * hash + Objects.hashCode(this.description);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final EvalType other = (EvalType) obj;
		if (!Objects.equals(this.description, other.description)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EvalType{" + "id=" + id + ", description=" + description + '}';
	}
	
}
