package user;

public class Quiz {
	private boolean randomQ;
	private boolean multiPage;
	private boolean immediateCorrection;
	private boolean practiceMode;
	private String author;
	
	
	public Quiz(String author, boolean randomQ, boolean multiPage, boolean immediateCorrection, boolean practiceMode) {
		this.author = author;
		this.randomQ = randomQ;
		this.multiPage = multiPage;
		this.immediateCorrection = immediateCorrection;
		this.practiceMode = practiceMode;
	}


	public boolean isRandomQ() {
		return randomQ;
	}


	public void setRandomQ(boolean randomQ) {
		this.randomQ = randomQ;
	}


	public boolean isMultiPage() {
		return multiPage;
	}


	public void setMultiPage(boolean multiPage) {
		this.multiPage = multiPage;
	}


	public boolean isImmediateCorrection() {
		return immediateCorrection;
	}


	public void setImmediateCorrection(boolean immediateCorrection) {
		this.immediateCorrection = immediateCorrection;
	}


	public boolean isPracticeMode() {
		return practiceMode;
	}


	public void setPracticeMode(boolean practiceMode) {
		this.practiceMode = practiceMode;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}

}
