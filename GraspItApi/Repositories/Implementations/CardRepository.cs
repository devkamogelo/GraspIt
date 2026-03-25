using Microsoft.EntityFrameworkCore;

public class CardRepository : ICardRepository
{
    private readonly AppDbContext _db;
    
    public CardRepository(AppDbContext db)
    {
        _db = db;
    }

    public async Task<IEnumerable<Card>> GetAllCards(int deckId)
    {
        var cards = await _db.Cards.Where(c => c.DeckId == deckId).ToListAsync();
        return cards;

    }
    public async Task<Card?> GetCardById(int id)
    {
        var card = await _db.Cards.FindAsync(id);
        if (card is null)
        {
            return null;
        }
        return card;
    }
    public async Task<Card> CreateCard(Card newCard)
    {
        _db.Cards.Add(newCard);
        await _db.SaveChangesAsync();
        return newCard;
    }

    public async Task<bool> UpdateCard(Card updatedCard, int id)
    {
        var card = await _db.Cards.FindAsync(id);
        if (card is null)
        {
            return false;
        }

        card.Front = updatedCard.Front;
        card.Back = updatedCard.Back;

        await _db.SaveChangesAsync();
        return true;

    }
    public async Task<bool> DeleteCard(int id)
    {
        var card = await _db.Cards.FindAsync(id);
        if (card is null)
        {
            return false;
        }

        _db.Cards.Remove(card);
        await _db.SaveChangesAsync();
        return true;
    }
}