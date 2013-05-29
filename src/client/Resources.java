package client;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Resources {
	public Image img_10_of_clubs, img_10_of_diamonds, img_10_of_hearts,
	img_10_of_spades, img_2_of_clubs, img_2_of_diamonds,
	img_2_of_hearts, img_2_of_spades, img_3_of_clubs,
	img_3_of_diamonds, img_3_of_hearts, img_3_of_spades,
	img_4_of_clubs, img_4_of_diamonds, img_4_of_hearts,
	img_4_of_spades, img_5_of_clubs, img_5_of_diamonds,
	img_5_of_hearts, img_5_of_spades, img_6_of_clubs,
	img_6_of_diamonds, img_6_of_hearts, img_6_of_spades,
	img_7_of_clubs, img_7_of_diamonds, img_7_of_hearts,
	img_7_of_spades, img_8_of_clubs, img_8_of_diamonds,
	img_8_of_hearts, img_8_of_spades, img_9_of_clubs,
	img_9_of_diamonds, img_9_of_hearts, img_9_of_spades,
	img_ace_of_clubs, img_ace_of_diamonds, img_ace_of_hearts,
	img_ace_of_spades, img_jack_of_clubs, img_jack_of_diamonds,
	img_jack_of_hearts, img_jack_of_spades, img_king_of_clubs,
	img_king_of_diamonds, img_king_of_hearts, img_king_of_spades,
	img_queen_of_clubs, img_queen_of_diamonds, img_queen_of_hearts,
	img_queen_of_spades, 
	desk, hit_up,	hit_down, stand_up, stand_down, start_up, start_down, refresh_up, refresh_down;
	public Sound click;
	
	public void load() throws SlickException {
        img_10_of_clubs       = new Image("images/cards/10_of_clubs.png");
        img_10_of_diamonds    = new Image("images/cards/10_of_diamonds.png");
        img_10_of_hearts      = new Image("images/cards/10_of_hearts.png");
        img_10_of_spades      = new Image("images/cards/10_of_spades.png");
        img_2_of_clubs        = new Image("images/cards/2_of_clubs.png");
        img_2_of_diamonds     = new Image("images/cards/2_of_diamonds.png");
        img_2_of_hearts       = new Image("images/cards/2_of_hearts.png");
        img_2_of_spades       = new Image("images/cards/2_of_spades.png");
        img_3_of_clubs        = new Image("images/cards/3_of_clubs.png");
        img_3_of_diamonds     = new Image("images/cards/3_of_diamonds.png");
        img_3_of_hearts       = new Image("images/cards/3_of_hearts.png");
        img_3_of_spades       = new Image("images/cards/3_of_spades.png");
        img_4_of_clubs        = new Image("images/cards/4_of_clubs.png");
        img_4_of_diamonds     = new Image("images/cards/4_of_diamonds.png");
        img_4_of_hearts       = new Image("images/cards/4_of_hearts.png");
        img_4_of_spades       = new Image("images/cards/4_of_spades.png");
        img_5_of_clubs        = new Image("images/cards/5_of_clubs.png");
        img_5_of_diamonds     = new Image("images/cards/5_of_diamonds.png");
        img_5_of_hearts       = new Image("images/cards/5_of_hearts.png");
        img_5_of_spades       = new Image("images/cards/5_of_spades.png");
        img_6_of_clubs        = new Image("images/cards/6_of_clubs.png");
        img_6_of_diamonds     = new Image("images/cards/6_of_diamonds.png");
        img_6_of_hearts       = new Image("images/cards/6_of_hearts.png");
        img_6_of_spades       = new Image("images/cards/6_of_spades.png");
        img_7_of_clubs        = new Image("images/cards/7_of_clubs.png");
        img_7_of_diamonds     = new Image("images/cards/7_of_diamonds.png");
        img_7_of_hearts       = new Image("images/cards/7_of_hearts.png");
        img_7_of_spades       = new Image("images/cards/7_of_spades.png");
        img_8_of_clubs        = new Image("images/cards/8_of_clubs.png");
        img_8_of_diamonds     = new Image("images/cards/8_of_diamonds.png");
        img_8_of_hearts       = new Image("images/cards/8_of_hearts.png");
        img_8_of_spades       = new Image("images/cards/8_of_spades.png");
        img_9_of_clubs        = new Image("images/cards/9_of_clubs.png");
        img_9_of_diamonds     = new Image("images/cards/9_of_diamonds.png");
        img_9_of_hearts       = new Image("images/cards/9_of_hearts.png");
        img_9_of_spades       = new Image("images/cards/9_of_spades.png");
        img_ace_of_clubs      = new Image("images/cards/ace_of_clubs.png");
        img_ace_of_diamonds   = new Image("images/cards/ace_of_diamonds.png");
        img_ace_of_hearts     = new Image("images/cards/ace_of_hearts.png");
        img_ace_of_spades     = new Image("images/cards/ace_of_spades.png");
        img_jack_of_clubs     = new Image("images/cards/jack_of_clubs.png");
        img_jack_of_diamonds  = new Image("images/cards/jack_of_diamonds.png");
        img_jack_of_hearts    = new Image("images/cards/jack_of_hearts.png");
        img_jack_of_spades    = new Image("images/cards/jack_of_spades.png");
        img_king_of_clubs     = new Image("images/cards/king_of_clubs.png");
        img_king_of_diamonds  = new Image("images/cards/king_of_diamonds.png");
        img_king_of_hearts    = new Image("images/cards/king_of_hearts.png");
        img_king_of_spades    = new Image("images/cards/king_of_spades.png");
        img_queen_of_clubs    = new Image("images/cards/queen_of_clubs.png");
        img_queen_of_diamonds = new Image("images/cards/queen_of_diamonds.png");
        img_queen_of_hearts   = new Image("images/cards/queen_of_hearts.png");
        img_queen_of_spades   = new Image("images/cards/queen_of_spades.png");
        desk                  = new Image("images/background/desk.jpg");
        hit_up                = new Image("images/buttons/hit_up.png");
        hit_down              = new Image("images/buttons/hit_down.png");
        stand_up              = new Image("images/buttons/stand_up.png");
        stand_down            = new Image("images/buttons/stand_down.png");
        start_up			  = new Image("images/buttons/start_up.png");
        start_down			  = new Image("images/buttons/start_down.png");
	}
}
