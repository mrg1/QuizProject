package quiz;

public class AverageRating {
	private double rating;
	private int quizId;
	
	public AverageRating(int quizId, double rating){
		this.rating = rating;
		this.quizId = quizId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
}
