package question;

public class PictureQuestion implements Question {

	public static final String DISPLAY_NAME = "Picture-Response Question";
	private String url;
	private int weight;
	private boolean caseSensitive;
	
	public PictureQuestion(String url, String[] answers) {
		this.url = url;
		this.weight = 1;
		caseSensitive = false;
	}
	
	public PictureQuestion(String url, String[] answers, int weight) {
		this.url = url;
		this.weight = weight;
		caseSensitive = false;
	}
	
	public boolean getCaseSensitive() {
		return caseSensitive;
	}
	
	public void setCaseSensitive(boolean bool) {
		caseSensitive = bool;
	}
	
	public String getPictureURL() {
		return url;
	}
	
	public void setPictureURL(String str) {
		url = str;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int w) {
		weight = w;
	}
	
	@Override
	public int checkAnswer(String userAnswer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxScore() {
		return weight;
	}

	@Override
	public String getHTML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	public static String getBuilderHTML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static PictureQuestion parseXML(String XML) {
		//TODO
		return null;
	}
}
