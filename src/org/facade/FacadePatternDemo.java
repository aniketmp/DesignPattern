package org.facade;


//*Facade pattern provides a unified interface to a set of interfaces in the subsystems.Facade defines a higher level interface that make 
// subsystem easier to use.
//*Facade pattern alters the interface to simplify it. It simply makes an interface simpler.
public class FacadePatternDemo {

	public static void main(String[] args) {
		//stage1:Watching the movie (the hard way)
		//Player player=new CdPlayer();
		Player player=new DvdPlayer();
		
		PopcornPopper popper=new PopcornPopper();
		popper.on();
		popper.pop();
		
		Screen screen=new Screen();
		screen.down();
		
		Projector projector=new Projector();
		projector.setInput(player);
		projector.wideScreenMode();
		
		Amplifier amplifier=new Amplifier();
		amplifier.setInput(player);
		amplifier.setSurroundSound();
		amplifier.setVolume(5);
		
		TheatorLights light=new TheatorLights();
		light.dim();
		
		player.on();
		player.startMovie();
		
		/*There are some disadvantages..
		 * This system is very complicated.
		 * When the movie is over, how do you turn everything off?
		 * Wouldn't you have to do all of this again, in reverse?
		 */
		//The only solution is Facade thats what we need.
		System.out.println("=============Watch movie using Facade thrator system!===========================");
		HomeTheatorFacade homeTheatorFacade=new HomeTheatorFacade(amplifier, new Tuner(), player, projector, light, screen, popper);
		homeTheatorFacade.watchMovie();
		homeTheatorFacade.endMovie();

	}

}


class PopcornPopper {
	public void on()
	{
		System.out.println("Turned ON popcorn popper");
	}
	public void pop()
	{
		System.out.println("Start the popcorn popping");
	}
	public void off() {
		System.out.println("Popcorn popper OFF");
		
	}
}
class Tuner 
{

}
class Screen 
{
	public void down()
	{
		System.out.println("Put the screen down");
	}

	public void up() {
		System.out.println("Theator screen going up");
		
	}
}

class TheatorLights 
{
	public void dim()
	{
		System.out.println("Dim the lights");
	}	
	public void on() 
	{
		System.out.println("Turn ON the threator lights back to normal");
		
	}
}

class Projector 
{
	Player player;
	public void on()
	{
		System.out.println("Turn the projector ON");
	}
	public void setInput(Player player)
	{
		this.player=player;
		System.out.println("Set the projcetor input to "+player.getName());
	}
	public void wideScreenMode()
	{
		System.out.println("Put the projcetor on wide screen mode");
	}
	public Player getInput()
	{
		return player;
	}
	public void off() {
		System.out.println("Turn OFF the projcetor");
		
	}
}

interface Player {
	public String getName();
	public void on();
	public void startMovie();
	public void stop();
	public void eject();
	public void off();
}

class DvdPlayer implements Player
{
	public void on()
	{
		System.out.println("Turn the DVD player ON");
	}
	public void startMovie()
	{
		System.out.println("Start the DVD player playing");
	}
	@Override
	public String getName() {
		return "DVD";
	}
	@Override
	public void stop() {
		System.out.println("Stop the DVD player");	
	}
	@Override
	public void eject() {
		System.out.println("Eject the DVD player");
		
	}
	@Override
	public void off() {
		System.out.println("Turn OFF the CD player");
		
	}
}
class CdPlayer implements Player
{
	public void on()
	{
		System.out.println("Turn the CD player ON");
	}
	public void startMovie()
	{
		System.out.println("Start the CD player playing");
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "CD";
	}
	@Override
	public void stop() {
		System.out.println("Stop the CD player");
		
	}
	@Override
	public void eject() {
		System.out.println("Eject the CD player");
		
	}
	@Override
	public void off() {
		System.out.println("Turn OFF the CD player");
		
	}
	
}


class Amplifier 
{
	public void on()
	{
		System.out.println("Turn the sound amplier ON");
	}
	public void setInput(Player player)
	{
		System.out.println("Set the amplier to "+player.getName()+" input");
	}
	public void setSurroundSound()
	{
		System.out.println("Set the amplier to surround sound");
	}
	public void setVolume(int level)
	{
		System.out.println("set the amplifier volumn to "+level);
	}
	public void off() {
		System.out.println("Turn OFF the amplifier");
		
	}
}

class HomeTheatorFacade
{
	Amplifier amp;
	Tuner tuner;
	Player player;	
	Projector projector;
	TheatorLights lights;
	Screen screen;
	PopcornPopper popper;
	public HomeTheatorFacade(Amplifier amp,Tuner tuner, Player player, Projector projector,
			TheatorLights lights, Screen screen, PopcornPopper popper) {
		super();
		this.amp = amp;		
		this.player= player;		
		this.projector = projector;
		this.lights = lights;
		this.screen = screen;
		this.popper = popper;
	}
	
	public void watchMovie()
	{
		popper.on();
		popper.pop();
				
		screen.down();
				
		projector.setInput(player);
		projector.wideScreenMode();
				
		amp.setInput(player);
		amp.setSurroundSound();
		amp.setVolume(5);
				
		lights.dim();
		
		player.on();
		player.startMovie();
	}
	public void endMovie() {
		popper.off();				
		
		screen.up();
		
		projector.off();
				
		amp.off();
				
		lights.on();
		
		player.stop();
		player.eject();
		player.off();
		
	}
	
}
