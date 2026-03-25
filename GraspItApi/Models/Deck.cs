public class Deck
{
    public int DeckId {get; set;}
    public int? UserId {get; set;}
    public User? User{get; set;}
    public List<Card>? Cards {get; set;}
    public required string Name {get; set;}
    public required string Description {get; set;}
} 