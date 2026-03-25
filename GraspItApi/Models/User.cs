public class User
{
    public int UserId{get; set;}
    public required string Name{get; set;}
    public required string GoogleId{get; set;}
    public required string Email{get; set;}
    public List<Deck>? Decks{get; set;}
}