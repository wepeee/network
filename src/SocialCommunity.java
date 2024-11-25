import java.util.ArrayList;
import java.util.List;

class Member {
    private String name;
    private List<Message> messages;

    public Member(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void createMessage(String content, Community community) {
        Message message = new Message(this, content);
        messages.add(message);
        community.addMessage(message);
        System.out.println(name + " created a message: " + content);
    }

    public void giveLike(Message message) {
        message.addLike(this);
    }

    public List<Message> getMessages() {
        return messages;
    }
}

class Message {
    private Member author;
    private String content;
    private List<Member> likes;

    public Message(Member author, String content) {
        this.author = author;
        this.content = content;
        this.likes = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public Member getAuthor() {
        return author;
    }

    public void addLike(Member member) {
        if (!likes.contains(member)) {
            likes.add(member);
            System.out.println(member.getName() + " liked: " + content);
        }
    }

    public int getTotalLikes() {
        return likes.size();
    }
}

class Community {
    private List<Member> members;
    private List<Message> messages;

    public Community() {
        members = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public Member registerMember(String name) {
        Member member = new Member(name);
        members.add(member);
        System.out.println("New member joined: " + name);
        return member;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void displayAllMessages() {
        System.out.println("\nAll Messages:");
        for (Message message : messages) {
            System.out.println(message.getAuthor().getName() + " wrote: " + message.getContent() + " - Likes: " + message.getTotalLikes());
        }
    }

    public void showMostActiveMember() {
        Member mostActive = null;
        int maxMessages = 0;

        for (Member member : members) {
            if (member.getMessages().size() > maxMessages) {
                mostActive = member;
                maxMessages = member.getMessages().size();
            }
        }

        if (mostActive != null) {
            System.out.println("\nMost Active Member: " + mostActive.getName() + " with " + maxMessages + " messages.");
        } else {
            System.out.println("\nNo active members yet.");
        }
    }
}

public class SocialCommunity {
    public static void main(String[] args) {
        Community community = new Community();

        Member alice = community.registerMember("Alice");
        Member bob = community.registerMember("Bob");

        alice.createMessage("Hello, Alice's first message!", community);
        bob.createMessage("Hey everyone, Bob is here!", community);

        bob.giveLike(alice.getMessages().get(0));
        alice.giveLike(bob.getMessages().get(0));

        community.displayAllMessages();

        community.showMostActiveMember();
    }
}
