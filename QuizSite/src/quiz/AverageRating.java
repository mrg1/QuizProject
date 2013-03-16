package quiz;

public class AverageRating {
	private Double rating;
	private Integer quizId;
	
	public AverageRating(Integer quizId, Double rating){
		this.rating = rating;
		this.quizId = quizId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}
}
