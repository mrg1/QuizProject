package db;

import java.util.List;
import user.*;
import message.*;

import org.junit.Test;

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
	public void testQuiz(){
		boolean rq = false;
		boolean ic = true;
		boolean mp = false;
		boolean pm = true;
		String author = "mrg";
		int quizId = UserInfo.addQuiz(author, rq, mp, ic, pm);
		Quiz q = UserInfo.getQuiz(quizId);
		assertTrue(q.getAuthor().equals(author));
		assertTrue(q.isRandomQ() == false);
		assertTrue(q.isImmediateCorrection() == true);
		assertTrue(q.isMultiPage() == false);
		assertTrue(q.isPracticeMode() == true);
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
		assertTrue(list.get(0).equals(c));
		UserInfo.addMessage(n);
		list = UserInfo.getMessages("mrg1");
		assertTrue(list.get(0).equals(c));
		assertTrue(list.get(1).equals(n));
		UserInfo.addMessage(r);
		list = UserInfo.getMessages("mrg1");
		assertTrue(list.get(0).equals(c));
		assertTrue(list.get(1).equals(n));
		assertTrue(list.get(2).equals(r));
		UserInfo.deleteMessages(r);
		UserInfo.deleteMessages(c);
		UserInfo.deleteMessages(n);
	}
}
