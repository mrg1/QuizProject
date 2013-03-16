package quiz;

public class Rating {
	private int rating;
	private String username;
	private int quizId;
	private String review;
	
	public Rating(String username, int quizId, int rating, String review){
		this.quizId = quizId;
		this.rating = rating;
		this.username = username;
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	
}
