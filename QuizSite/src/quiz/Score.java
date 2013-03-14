package quiz;

public class Score {
	private int score;
	private int quizId;
	private String username;
	private int seconds;
	
	public Score(int score, int quizId, String username, int elapsed){
		this.score = score;
		this.quizId = quizId;
		this.username = username;
		this.seconds = elapsed;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getElapsed() {
		return seconds;
	}
	
	public void setElapsed(int sec) {
		seconds = sec;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quizId;
		result = prime * result + score;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (quizId != other.quizId)
			return false;
		if (score != other.score)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
	
	
}
