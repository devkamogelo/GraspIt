public class Card
{
    public int CardId {get; set;}
    public int DeckId {get; set;}
    public Deck? Deck{get; set;}
    public required string Front {get; set;}
    public required string Back {get; set;}
}