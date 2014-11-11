/*program to demonstrate the social network project through the 
implementation of
 											*OOPS
 											*Exception Handling
 											*Inheritance
Main Class is SocialNetwork which employs the main method
which calls the menu method to display the menu and
take inputs 
class Database 
 					*Creates dataStructure which is a hash table
 					*adds new user and updates the hash table
 					*prints data structure
 					*has method which gives the names of all users present
 					*gives number of users in the database
 					*and employs test method which handles the exception of NoSuchUser
 
class user inherits from Database so as to access network(hash table)
 					*gives user name
 					*method to give a string array of all the connections that a particular user has
 					*method to give a string array of all the games that a particular user plays
 					*method to add connection between two users
 
 class connections inherits from Database and employs the following methods
 					*to get the names of the users that a particular user is connected to
 					*to give the secondary connections
 					*to give the names of common connections between two users
 					*to give the path between two users
 
 class Games also Inherits from Database and employs
 					*to give a string array of games which a particular user plays
 					*to give SIMILARITY INDEX between two users   
 														* Similarity index is based on the formula
 														     (number of common games between the given user and any other user/number of games the given user plays)*/
package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class SocialNetwork 
{
	public static void main(String[] args) throws Exception 
	{
		try
		{
		menu();
		}catch(FileNotFoundException e)
		{}
	}
	
	public static void menu() throws Exception,FileNotFoundException
	{
		
		Database dbase=new Database();
		
		dbase.printDataStructure();
		boolean input=true;
		
		Scanner in=new Scanner(System.in);	
		int option=1;
		
			
		
				do{	//main menu		
					System.out.println("\n____________________WELCOME TO GAMING NETWORK_________________");
					System.out.println("1.Get Connections \t   2.Add Connection\t    3.Add new user");
					System.out.println("4.Get Connections in common\t 5.Get secondary connections\t 6.Get path to friend");
					System.out.println("7.Get Similarity\t  8.Exit");
					System.out.println("Enter your option");
					option=in.nextInt();		
					
					switch(option)
					{
						case 1:			//using method to know the connections of an already existing user
									while(input)
									{
										try
										{								
										System.out.println("Enter the name of the user to getConnections:\n");								
										String user_A=in.next();
										
										dbase.test(user_A);
									
										User us1=new User(user_A);										
										Connections con=new Connections(us1.getAllConnections());
										
										System.out.println(con.getConnections());
										input=false;
										}catch(NoSuchUserException e){
											System.out.println(e);	}								
									}
									break;
									
						case 2:         //using method to add the connection between users
									try
										{
										System.out.println("Enter the names of the users to connection:\n 1.  ");
										String user_A=in.next();
										dbase.test(user_A);
										User us1=new User(user_A);
										System.out.println("2. ");
										String user_B=in.next();
										dbase.test(user_B);
										User us2=new User(user_B);
										us1.addConnections(us2);
										}catch(NoSuchUserException e){
										System.out.println(e);									
										}
										break;
								
						case 3:			//using method to add new user to the network
								
										System.out.println("Enter the name of new user:\t");
										String user_A=in.next();
										User us1=new User(user_A);										
										System.out.println("\nEnter the number of games that user wants to play:  ");
										int n_games=in.nextInt();							
										String[] user_games=new String[n_games];
										in.nextLine();
										System.out.println("\nEnter the names of games that user wants to play:  "+user_games.length);
										int n = 0;
										for(n=0;n<user_games.length;n++)
										{								
											user_games[n]=in.nextLine();
										}
		
										dbase.addNewUser(us1,user_games);
									
									
										break;						
				
						case 4:			//to get the common connections between two users
										try
										{
											System.out.println("Enter the names of the users to get the number of connections in common:\n 1.  ");
											user_A=in.next();
											dbase.test(user_A);
											us1=new User(user_A);
											Connections cn1=new Connections(us1.getAllConnections());
					
											System.out.println("2. ");
											String user_B=in.next();
											dbase.test(user_B);
											User us2=new User(user_B);
											Connections cn2=new Connections(us2.getAllConnections());
											System.out.println(" common connections: "+cn1.getConnectionsInCommon(cn2));
											} catch(NoSuchUserException e){
												System.out.println(e);
											}
										break;
					
						case 5:			//getting secondary connections of a user
										try
										{
											System.out.println("Enter the name of the user to get the secondary connections:\n");
											user_A=in.next();
											dbase.test(user_A);
											us1=new User(user_A);
											Connections cn1=new Connections(us1.getAllConnections());
											System.out.println(cn1.getSecondaryConnections(us1));
										} catch(NoSuchUserException e){
											System.out.println(e);
										}
										break;
		
						case 6:         //getting path to friend
										try
										{
											System.out.println("\nEnter the names of the users between which u want to find the path\n1.");
       
											user_A=in.next();
											dbase.test(user_A);
											us1=new User(user_A);
											Connections cn1=new Connections(us1.getAllConnections());
        			
											System.out.println("2. ");
											String user_B=in.next();
											dbase.test(user_B);
											User us2=new User(user_B);
											Connections cn2=new Connections(us2.getAllConnections());
        			
											String[] path=cn1.pathToFriend(cn2,us1,us2);
											if(path.length==0)
												System.out.println("\nno connection between the two");
											else
											{
												for(int i=0;i<path.length;i++)
													System.out.print(path[i]+"  ");
											}
											System.out.print("\n");
											
										}catch(NoSuchUserException e){
											System.out.println(e);
										}
										break;	
		
						case 7:		    try
										{
											System.out.println("Enter the user name to find similarity of :");
											user_A=in.next();		
											us1=new User(user_A);
											dbase.test(user_A);
											Games gm=new Games(us1.getAllGames());
											gm.degreeSimilarity(us1);
										}catch(NoSuchUserException e)
										{
											System.out.println(e);
										}
										break;
				case 8:         System.exit(1);	
			}
	}while(option!=8);
  
  }
}

	
class Database
{
	protected Hashtable <String , String> network;
	private int user_count=0;
	public Database()
	{
		CreateDataStructure("Database.txt");
	}
	
	
	//method to create data structure
	void CreateDataStructure(String filename)
	{
		network=new Hashtable <String , String>();

		try{
		
		BufferedReader br=new BufferedReader(new FileReader(filename));	
			
		// strings to store the key.i.e user name for hash table games and connections
		String key;
			
		//read the whole file one line at a time
		String line=br.readLine();
		while(line!=null)
		{
			String val="";
			String[] connection=line.split(" is connected to ");
			key=connection[0];
			
			val+=connection[1];
			String[] strTemp1=val.split("\\.");
        	val=strTemp1[0]+"/";
			
			
			line=br.readLine();
			String[] game=line.split(" likes to play ");
			String gameTemp=game[1];
			String[] strTemp2=gameTemp.split("\\.");
			gameTemp=strTemp2[0];
			val+=gameTemp;
			

			//filling the hash table 
		    network.put(key,val);
	    	line=br.readLine();
						
		}
		br.close();

		

	} catch (FileNotFoundException e) 
	        {
             System.err.println("FileNotFoundException: " + e.getMessage());
            }
            catch (IOException e)
            {
              System.err.println("Caught IOException: " + e.getMessage());
            }
				
	}
	
	//prints the data structure when called 
	void printDataStructure()
	{
		for(String itm:network.keySet())
		{
			user_count++;
			System.out.print(itm+"----");
			System.out.println(network.get(itm));
		}
		System.out.println("\nDatastructure successfully created\n");
	}
	
	User[] getAllUsers()
	{
		User[] user=new User[user_count];
		int i=0;
		for(String itm:network.keySet())
			{
				user[i]=new User(itm);
				i++;
			}
		return user;
	}
	
	
	
	//method to add new user to the network
	void addNewUser(User user,String[] games)
	{
		String key,val;
		key=user.getName();		
		val="/";
		for(int i=0;i<games.length;i++)
		{
			val+=games[i];
			val+=", ";
		}
		val=val.substring(0,val.length()-2);
		network.put(key,val);
		
        // printing the whole new database with the added user and his games
		for(String itm:network.keySet())
		{
			System.out.print(itm+"----");
			System.out.println(network.get(itm));
		}
		
	}
	Hashtable <String , String> getDataStructure()		{return network;}
	
	int getUserCount()									{return user_count;}
	
	public void test(String user) throws NoSuchUserException
	{
		boolean flag=false;
		for(String itm:network.keySet())
		{
			if(user.equals(itm))
				flag=true;
		}
		if(flag==false)
			throw new NoSuchUserException();
	}
}

class User extends Database
{
	private String name;	
	
	
	public User(String key) 
	{
		
		name=key;		
		
	}
	String getName()		{return name;}
	
	//returns the string array of all connections of the user
	String[] getAllConnections()
	{
		String s="";
		
		for(String itm:network.keySet())
		{
				
				if(itm.equals(this.name))
				{
					String s1=network.get(itm);
					String[] connections=s1.split("/");
					s+=connections[0];
				}	
		}
		
		String[] connections1=s.split(", ");
		
		return connections1;
	}
	
	//returns the string array of games
	String[] getAllGames()
	{
		String s="";
		
		for(String itm:network.keySet())
		{
			
			if(itm.equals(this.name))
			{
				String s1=network.get(itm);
				String[] connections=s1.split("/");
				s+=connections[1];
				//System.out.println(this.getName()+" "+s);
				break;
			}
		}
		
		String[] games1=s.split(", ");
		
		return games1;
	}
	
	//method to establish a new connection
	void addConnections(User user_B)
		{
			boolean flag=false;		
			
			String[] c1=this.getAllConnections();
			for(int i=0;i<c1.length;i++)
				if(user_B.name.equals(c1[i]))
					 {
						System.out.println(this.name+" is already connected to "+user_B.name);        //can make exception
						flag=true;
					 }
			
				if(flag==false)
				{
					String[] val1=new String[2];
					String val2="";	
					val2+=network.get(this.name);
					val1=val2.split("/");
					val1[0]+=", ";
					val1[0]+=user_B.name;
					val2="";
					val2+=val1[0]+"/"+val1[1];
					network.put(this.name,val2);
					System.out.println("\nconnected");
					System.out.println("connections of "+this.name+" : "+val1[0]+"\n");
				}		
							
			}
		
	
}



class Connections  extends Database
{
	public ArrayList<String> mark=new ArrayList<String>();	
	private String[] connections;
	private ArrayList<String> sec_con=new ArrayList<String>();
	
	public Connections(String []connection) 
	{
		connections=new String[connection.length];
		for(int i=0;i<connection.length;i++)
			connections[i]=connection[i];
	}
	
	String getConnections()
	{	
		String s="";
		for(int i=0;i<connections.length;i++)
			s+=connections[i]+" ";
		return s;
	}
	
	String getSecondaryConnections(User us)
	{		
		//array stores the list of primary connections of user
	  
	    User us1;
	    for(int i=0;i<this.connections.length;i++)
	    {  
	    	
	    	us1=new User(this.connections[i]);
	    	
	    	//to get connections of each of the connections of the user
	    	String[] c2=us1.getAllConnections();
	    	
	    	for(int j=0;j<c2.length;j++)
	    		{
	    			sec_con.add(c2[j]);
	    			
	    		}
	    	
	    }
	    	
	    String[] final_con=new String[sec_con.size()];	    
	    final_con=sec_con.toArray(final_con);	    
	    sec_con.clear();
	    
	    String s="";
	    
	    //removing duplicacy
	    for(int i=0;i<final_con.length;i++)
	    {
	    	if(!sec_con.contains(final_con[i]))
	    		sec_con.add(final_con[i]); 	    		
	    }
	   
	    
	    String[] str=new String[sec_con.size()];
	    str=sec_con.toArray(str);
	    
	    
	    //remove primary user connections and user name
	    for(int i=0;i<str.length;i++)
	    {
	    	boolean flag=true;
	    	for(int j=0;j<connections.length;j++)
	    		if((!str[i].equals(connections[j]))&&(!str[i].equals(us.getName())))
	    			flag=false;
	    	if(flag==false)
	    		s+=str[i]+" ";
	    	
	    }
	   
		return s;
	}
	
	String getConnectionsInCommon(Connections cn)
	{
				
		String s="";
		
		for(int i=0;i<this.connections.length;i++)
			for(int j=0;j<cn.connections.length;j++)
				if(this.connections[i].equals(cn.connections[j]))
					s+=this.connections[i];
		return s;
	}
	
	 String[] pathToFriend(Connections cn,User user_A,User user_B)
	{
		//mark denoted the globally declared array list which is storing nodes used
		if(mark.contains(user_A.getName())==false)
	    mark.add(user_A.getName());
	    
		//converting array list into string array to match the return type
	    String[] path=new String[mark.size()];
        path=mark.toArray(path);
        
        //using stack to push and pop the elements
        Stack st=new Stack();
        st.push(user_A.getName());
        
        //directly returns if the user_A becomes same as the string user_B
        if(user_A.getName().equalsIgnoreCase(user_B.getName()))
        	return path;
        
        //putting in loop to continue popping from the array if all the elements connected to the given node have been checked
        boolean flag=true;
        while(flag=true)
        {
        	//Connections cn=new Connections(user_A);
        	//String[] s=getConnections(user_A);
        	if(this.connections.length==0)
        		break;
        	
    	    for(int i=0;i<this.connections.length;i++)
    	       	{
    	    	flag=mark.contains(this.connections[i]);
    	    	user_A=new User(this.connections[i]);
    	    	if(flag==false) 
    	    		return pathToFriend(cn,user_A,user_B); 
    	       	}    	    
    	    if((flag==true)&&(st.empty()==false))
    	    	{
    	    		st.pop();
    	    		user_A=new User((String) st.peek());
    	    	}
    	    
    	    else if(st.empty()==true)
    	    	return path;
    	    
    	    else 
    	    	return pathToFriend(cn,user_A,user_B);       	    
        }
        return path;
	}
	
}



class Games extends Database
{
	private String[] games;
	
	public Games(String[] game)
	{
		games=new String[game.length];
		for(int i=0;i<game.length;i++)
			games[i]=game[i];
	}
	
	public String[] getGames()
	{
		return this.games;
	}
	void degreeSimilarity(User user)
	{
			
					
				//hash table to store the names of the users and the respective similarity index
				Hashtable <String , Double> sim_index=new Hashtable <String , Double>();
				String key2;   //key to the hash table will be the user names
				
				for(String itm:network.keySet())
				{
					if(user.getName().equals(itm)==false)
					{
						User us=new User(itm);
						
						//invoking getAllGames() to get the list of games which the users in the database play in string format
						String[] c2=us.getAllGames();	                  
						System.out.println(us.getName()+c2.length);
						key2=itm;
						int count=0;		
						for(int i=0;i<this.games.length;i++)
						{
							for(int j=0;j<c2.length;j++)
							{
								if(this.games[i].equalsIgnoreCase(c2[j]))									
									count+=1.0;				
							}	
						}
						
						//count1 contains the similarity index 
						//which is equal to no. of common games 
						//divided by no. of games the given user plays
						double count1=count/(this.games.length*1.0);      
						sim_index.put(key2, count1);		             //filling the data in hash table	
					}
				}
					
				
				System.out.println("\nSimilarity index is as follows:");
				
					for(String itm2:sim_index.keySet())        //printing the names of users who have similarity index greater than or equal to .5 with the given user
					{			
						if(sim_index.get(itm2)>=0.0)
						{					
							System.out.print(itm2+"------>");
							System.out.println(sim_index.get(itm2));
						}
					}
				
	}
}

//new exception class created to handle the wrong user name entered 
class NoSuchUserException extends Exception
{
	 private String message = "NoSuchUserException";
	 
	    public NoSuchUserException() {
	        super();
	    }
	 
	    public NoSuchUserException(String message) {
	        super(message);
	        this.message = message;
	    }
	 
	    public NoSuchUserException(Throwable cause) {
	        super(cause);
	    }
	 
	    @Override
	    public String toString() {
	        return message;
	    }
	 
	    @Override
	    public String getMessage() {
	        return message;
	    }
}