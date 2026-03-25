using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.EntityFrameworkCore;

public class DeckRepository : IDeckRepository
{
    private readonly AppDbContext _db;

    public DeckRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task<IEnumerable<Deck>> GetAllDecks(){
        var decks = await _db.Decks.ToListAsync();
        return decks;
    }
    public async Task<Deck?> GetDeckById(int id)
    {
        var deck = await _db.Decks.FindAsync(id);
        return deck;
    }
    public async Task<Deck> CreateDeck(Deck newDeck)
    {
        _db.Decks.Add(newDeck);
        await _db.SaveChangesAsync();
        return newDeck;
    }
    public async Task<bool> UpdateDeck(int id, Deck updatedDeck)
    {
        var deck = await _db.Decks.FindAsync(id);
        if(deck is null)
        {
            return false;
        } 
            deck.Name = updatedDeck.Name;
            deck.Description = updatedDeck.Description;
        await _db.SaveChangesAsync();
        return true;
    }
    public async Task<bool> DeleteDeck(int id)
    {
        var deck = await _db.Decks.FindAsync(id);
        if (deck is null)
        {
            return false;
        }
        _db.Decks.Remove(deck);
        await _db.SaveChangesAsync();
        return true;
    }
}