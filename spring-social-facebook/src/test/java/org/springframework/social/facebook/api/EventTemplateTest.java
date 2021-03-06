/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;

public class EventTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void getCreated_user() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/me/events/created?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getCreated();
		assertInvitations(events);
	}

	@Test
	public void getCreated_friend() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/123456789/events/created?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getFriendCreated("123456789");
		assertInvitations(events);
	}

	@Test
	public void getAttending_user() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/me/events/attending?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getAttending();
		assertInvitations(events);
	}

	@Test
	public void getAttending_friend() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/123456789/events/attending?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getFriendAttending("123456789");
		assertInvitations(events);
	}

	@Test
	public void getMaybe_user() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/me/events/maybe?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getMaybeAttending();
		assertInvitations(events);
	}

	@Test
	public void getMaybe_friend() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/123456789/events/maybe?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getFriendMaybeAttending("123456789");
		assertInvitations(events);
	}

	@Test
	public void getNoReplies_user() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/me/events/not_replied?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getNoReplies();
		assertInvitations(events);
	}

	@Test
	public void getNoReplies_friend() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/123456789/events/not_replied?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getFriendNoReplies("123456789");
		assertInvitations(events);
	}

	@Test
	public void getDeclined_user() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/me/events/declined?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getDeclined();
		assertInvitations(events);
	}

	@Test
	public void getDeclined_friend() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/123456789/events/declined?offset=0&limit=25"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("user-events"), MediaType.APPLICATION_JSON));
		List<Invitation> events = facebook.eventOperations().getFriendDeclined("123456789");
		assertInvitations(events);
	}

	@Test
	public void getEvent() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832?fields=id%2Ccover%2Cdescription%2Cend_time%2Cis_date_only%2Clocation%2Cname"
				+ "%2Cowner%2Cparent_group%2Cprivacy%2Cstart_time%2Cticket_uri%2Ctimezone%2Cupdated_time%2Cvenue"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("simple-event"), MediaType.APPLICATION_JSON));
		Event event = facebook.eventOperations().getEvent("193482154020832");
		assertSimpleEvent(event, Event.Privacy.OPEN);
	}
	
	@Test
	public void getEvent_withFriendPrivacyLevel() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832?fields=id%2Ccover%2Cdescription%2Cend_time%2Cis_date_only%2Clocation%2Cname"
				+ "%2Cowner%2Cparent_group%2Cprivacy%2Cstart_time%2Cticket_uri%2Ctimezone%2Cupdated_time%2Cvenue"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("simple-event-friend-privacy"), MediaType.APPLICATION_JSON));
		Event event = facebook.eventOperations().getEvent("193482154020832");
		assertSimpleEvent(event, Event.Privacy.FRIENDS);
	}
	
	@Test
	public void getEvent_withLocationAndDescription() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832?fields=id%2Ccover%2Cdescription%2Cend_time%2Cis_date_only%2Clocation%2Cname"
				+ "%2Cowner%2Cparent_group%2Cprivacy%2Cstart_time%2Cticket_uri%2Ctimezone%2Cupdated_time%2Cvenue"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("full-event"), MediaType.APPLICATION_JSON));
		Event event = facebook.eventOperations().getEvent("193482154020832");
		assertEquals("193482154020832", event.getId());
		assertEquals("100001387295207", event.getOwner().getId());
		assertEquals("Art Names", event.getOwner().getName());
		assertEquals("Breakdancing Class", event.getName());
		assertEquals(Event.Privacy.SECRET, event.getPrivacy());
		assertEquals(toDate("2011-03-30T14:30:00+0000"), event.getStartTime());
		assertEquals(toDate("2011-03-30T17:30:00+0000"), event.getEndTime());
		assertEquals(toDate("2011-03-30T14:38:40+0000"), event.getUpdatedTime());
		assertEquals("Bring your best parachute pants!", event.getDescription());
		assertEquals("2400 Dunlavy Dr, Denton, TX", event.getLocation());
	}
	
	@Test
	public void getInvited() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/invited"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("invited"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getInvited("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.ATTENDING);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.MAYBE);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.NOT_REPLIED);
	}
	
	@Test
	public void getAttending() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/attending"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("attending"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getAttending("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.ATTENDING);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.ATTENDING);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.ATTENDING);
	}
	
	@Test
	public void getMaybeAttending() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/maybe"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("maybe-attending"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getMaybeAttending("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.MAYBE);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.MAYBE);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.MAYBE);
	}
	
	@Test
	public void getNoReplies() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/noreply"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("no-replies"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getNoReplies("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.NOT_REPLIED);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.NOT_REPLIED);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.NOT_REPLIED);
	}
	
	@Test
	public void getDeclined() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/declined"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("declined"), MediaType.APPLICATION_JSON));
		List<EventInvitee> invited = facebook.eventOperations().getDeclined("193482154020832");
		assertEquals(3, invited.size());
		assertInvitee(invited.get(0), "100001387295207", "Art Names", RsvpStatus.DECLINED);
		assertInvitee(invited.get(1), "738140579", "Craig Walls", RsvpStatus.DECLINED);
		assertInvitee(invited.get(2), "975041837", "Chuck Wagon", RsvpStatus.DECLINED);
	}

	@Test
	public void acceptInvitation() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/attending"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().acceptInvitation("193482154020832");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void acceptInvitation_unauthorized() {
		unauthorizedFacebook.eventOperations().acceptInvitation("123456789");
	}

	@Test
	public void maybeInvitation() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/maybe"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().maybeInvitation("193482154020832");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void maybeInvitation_unauthorized() {
		unauthorizedFacebook.eventOperations().maybeInvitation("123456789");
	}
	
	@Test
	public void declineInvitation() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/193482154020832/declined"))
			.andExpect(method(POST))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("true", MediaType.APPLICATION_JSON));
		facebook.eventOperations().declineInvitation("193482154020832");
		mockServer.verify();
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void declineInvitation_unauthorized() {
		unauthorizedFacebook.eventOperations().declineInvitation("123456789");
	}

	@Test
	public void search() {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.2/search?offset=0&limit=25&q=Spring+User+Group&type=event"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("event-list"), MediaType.APPLICATION_JSON));
		List<Event> results = facebook.eventOperations().search("Spring User Group");
		assertEquals(1, results.size());
		assertEquals("196119297091135", results.get(0).getId());
		assertEquals("FLUG (Florida Local Users Group) Spring User Conference", results.get(0).getName());
		assertEquals("Radisson Resort at the Port", results.get(0).getLocation());
		assertEquals(toDate("2011-06-01T08:00:00+0000"), results.get(0).getStartTime());
		assertEquals(toDate("2011-06-03T16:00:00+0000"), results.get(0).getEndTime());
	}
	
	private void assertInvitee(EventInvitee invitee, String id, String name, RsvpStatus rsvpStatus) {
		assertEquals(id, invitee.getId());
		assertEquals(name, invitee.getName());
		assertEquals(rsvpStatus, invitee.getRsvpStatus());
	}
	
	private void assertInvitations(List<Invitation> events) {
		assertEquals(2, events.size());
		assertEquals("188420717869087", events.get(0).getEventId());
		assertEquals("Afternoon naptime", events.get(0).getName());
		assertEquals("On the couch", events.get(0).getLocation());
		// Facebook event times don't have a timezone component, so they end up parsed as in +0000
		// Unfortunately, this is probably not the actual time of the event.
		assertEquals(toDate("2011-03-26T14:00:00+0000"), events.get(0).getStartTime());
		assertEquals(toDate("2011-03-26T15:00:00+0000"), events.get(0).getEndTime());
		assertEquals(RsvpStatus.ATTENDING, events.get(0).getRsvpStatus());
		assertEquals("188420717869780", events.get(1).getEventId());
		assertEquals("Mow the lawn", events.get(1).getName());
		assertNull(events.get(1).getLocation());
		assertEquals(toDate("2011-03-26T15:00:00+0000"), events.get(1).getStartTime());
		assertEquals(toDate("2011-03-26T16:00:00+0000"), events.get(1).getEndTime());
		assertEquals(RsvpStatus.NOT_REPLIED, events.get(1).getRsvpStatus());
	}
	
	private void assertSimpleEvent(Event event, Event.Privacy privacy) {
		assertEquals("193482154020832", event.getId());
		assertEquals("100001387295207", event.getOwner().getId());
		assertEquals("Art Names", event.getOwner().getName());
		assertEquals("Breakdancing Class", event.getName());
		assertEquals(privacy, event.getPrivacy());
		assertEquals(toDate("2011-03-30T14:30:00+0000"), event.getStartTime());
		assertEquals(toDate("2011-03-30T17:30:00+0000"), event.getEndTime());
		assertEquals(toDate("2011-03-30T14:30:28+0000"), event.getUpdatedTime());
		Location venue = event.getVenue();
		assertEquals("215524685134868", venue.getId());
		assertEquals("Fort Worth", venue.getCity());
		assertEquals("United States", venue.getCountry());
		assertEquals("TX", venue.getState());
		assertEquals("921 Henderson St", venue.getStreet());
		assertEquals("76102", venue.getZip());
		assertEquals(32.7493792167, venue.getLatitude(), 0.00001);
		assertEquals(-97.3371771801, venue.getLongitude(), 0.00001);
		assertEquals("Some call it spin class", event.getDescription());
		assertEquals("Walgreens", event.getLocation());
		assertTrue(event.isDateOnly());
		CoverPhoto cover = event.getCover();
		assertEquals("14639096", cover.getId());
		assertEquals(0, cover.getOffsetX());
		assertEquals(50, cover.getOffsetY());
		assertEquals("https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-xpf1/t1.0-9/s720x720/10322540_1463971520512096_8756935_n.jpg", cover.getSource());
	}
	
}
