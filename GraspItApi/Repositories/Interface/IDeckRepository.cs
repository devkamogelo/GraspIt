public interface IDeckRepository
{
    Task<IEnumerable<Deck>> GetAllDecks();
    Task<Deck?> GetDeckById(int id);
    Task<Deck> CreateDeck(Deck newdeck);
    Task<bool> UpdateDeck(int id, Deck updatedDeck);
    Task<bool> DeleteDeck(int id);
}