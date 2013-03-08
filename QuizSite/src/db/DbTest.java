package db;

import java.util.*;
import user.*;
import message.*;

import org.junit.Test;

import question.*;
import quiz.Quiz;
import quiz.Score;

import junit.framework.TestCase;

public class DbTest extends TestCase {
	
	@Test
	public void testUser(){
		String username = "mrg";
		String password = "garland";
		String salt = "blah";
		boolean admin = true;
		UserInfo.deleteUser(username);
		UserInfo.addUser(username, password, salt, admin);
		assertTrue(UserInfo.userExists("notARealUser") == false);
		assertTrue(UserInfo.userExists("mrg") == true);
		assertTrue(UserInfo.checkPassword(username, "notPassword") == false);
		assertTrue(UserInfo.checkPassword(username, password) == true);
	}
	
	@Test
	public void testAch(){
		String username = "mrg1";
		Integer achID = 1;
		Integer secondAchId = 2;
		UserInfo.addAchievment(username, achID);
		List<Integer> achs = UserInfo.getAchievments(username);
		assertTrue(achs.get(0).equals(achID));
		UserInfo.addAchievment(username, secondAchId);
		achs = UserInfo.getAchievments(username);
		assertTrue(achs.get(0).equals(achID));
		assertTrue(achs.get(1).equals(secondAchId));
	}

	
	
	@Test
	public void testMessage(){
		String to = "mrg1";
		String from = "mrg2";
		String content = "This is a mesage from one user to another. Hopefully all of the message database stuff is working correctly and this message gets sent." +
				" Don't worry this message is meant only as a test. Nothing else";
		Request r = new Request(to, from, content);
		Note n = new Note(to, from, content);
		Challenge c = new Challenge(to, from, content);
		UserInfo.addMessage(c);
		List<Message> list = UserInfo.getMessages("mrg1");
		assertTrue(list.contains(c));
		UserInfo.addMessage(n);
		list = UserInfo.getMessages("mrg1");
		assertTrue(list.contains(c));
		assertTrue(list.contains(n));
		UserInfo.addMessage(r);
		list = UserInfo.getMessages("mrg1");
		assertTrue(list.contains(c));
		assertTrue(list.contains(n));
		assertTrue(list.contains(r));
		UserInfo.deleteMessages(r);
		UserInfo.deleteMessages(c);
		UserInfo.deleteMessages(n);
	}
	
	@Test
	public void testQuiz(){
		String[] answers = {"hello", "hola", "bonjour", "tag"};
		String question1 = "How do you say hello in Spanish?";
		String question2 = "How do you say hello?";
		MultipleChoiceQuestion q = new MultipleChoiceQuestion(question1, answers, "hola");
		PictureQuestion pq = new PictureQuestion(question1, answers, true, 1);
		ResponseQuestion rq = new ResponseQuestion(question2, answers);
		Question[] questions = {rq, pq};
		Quiz quiz = new Quiz("TestQuiz", "mrg1", "This is a test quiz", questions, false, true, true, false);
		Quiz quiz2 = new Quiz("TestQuiz", "mrg1", "This is a test quiz", questions, false, true, true, false);
		int qid = UserInfo.addQuiz(quiz);		
		int qid2 = UserInfo.addQuiz(quiz2);	
		Quiz gotQuiz = UserInfo.getQuiz(qid);
		assertTrue(gotQuiz.equals(quiz));
	}
	
	@Test
	public void testScoring(){
		Score s1 = new Score(76, 99, "mrg1");
		Score s2 = new Score(66, 100, "mrg1");
		Score s3 = new Score(79, 99, "aKlein1");
		UserInfo.addScore(s1);
		UserInfo.addScore(s2);
		UserInfo.addScore(s3);
		List<Score> scores = UserInfo.getHistory("mrg1");
		assertTrue(scores.contains(s1));
		assertTrue(scores.contains(s2));
		assertTrue(!scores.contains(s3));
		scores = UserInfo.getTopTen(99);
		assertTrue(scores.contains(s1));
		assertTrue(!scores.contains(s2));
		assertTrue(scores.contains(s3));
		UserInfo.deleteHistory("mrg1");
		UserInfo.deleteHistory("aKlein1");
		scores = UserInfo.getHistory("mrg1");
		assertTrue(!scores.contains(s1));
		assertTrue(!scores.contains(s2));
		assertTrue(!scores.contains(s3));
	}
	
	@Test
	public void testQuizDelete(){
		String[] answers = {"hello", "hola", "bonjour", "tag"};
		String question1 = "How do you say hello in Spanish?";
		String question2 = "How do you say hello?";
		MultipleChoiceQuestion q = new MultipleChoiceQuestion(question1, answers, "hola");
		ResponseQuestion rq = new ResponseQuestion(question2, answers);
		Question[] questions = {rq, q};
		Quiz quiz = new Quiz("TestQuiz", "mrg1", "This is a test quiz", questions, false, true, true, false);
		int qId = UserInfo.addQuiz(quiz);	
		UserInfo.deleteQuiz(qId);
		assertTrue(UserInfo.getQuiz(qId) == (null));
	}
}
