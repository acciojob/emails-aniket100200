package com.driver;

import java.time.LocalDate;
import java.util.*;

public class Gmail extends Email
{
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    Deque<Mail>inbox;

    //addLast ---> mail Addition..
    //latest mail last asnar

    //removeFirst()...
   ArrayList<Mail>trash;
    public Gmail(String emailId, int inboxCapacity)
    {
         super(emailId);
        this.inboxCapacity=inboxCapacity;
          inbox=new LinkedList<>();
          trash=new ArrayList<>();

    }



    public void receiveMail(Date date, String sender, String message)
    {
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.

        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size()==inboxCapacity)
        {

            Mail mail=inbox.removeFirst();
            trash.add(mail);

            ///add last hoto..
        }
        inbox.add(new Mail(date,sender,message));

    }

    public void deleteMail(String message)
    {
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        for(Mail mail:inbox)
        {
            if(mail.getMessage().equals(message)==true)
            {
              trash.add(mail);
              inbox.remove(mail);
              break;
            }
        }


    }

    public String findLatestMessage()
    {
        if(inbox.size()==0)return null;
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        Mail mail=inbox.getLast();
        return mail.getMessage();

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        if(inbox.size()==0)return null;
        Mail mail=inbox.getFirst();
        return mail.getMessage();

    }

    public int findMailsBetweenDates(Date start, Date end)
    {
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(Mail mail:inbox)
        {
            Date date=mail.getDate();
            if((date.before(start) && date.after(end)) || (!date.before(start) && !date.after(end)) )count++;

        }
        return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();


    }

    public void emptyTrash()
    {
        // clear all mails in the trash
        trash=new ArrayList<>();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}

class Mail
{
    private Date date;
    private String sender;
    private String message;
    public Mail(Date date,String sender,String message)
    {
        this.date=date;
        this.sender=sender;
        this.message=message;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }



    @Override
    public String toString()
    {
        return ""+date;
    }
}
