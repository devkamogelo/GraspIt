public interface ICardRepository
{
    Task<IEnumerable<Card>> GetAllCards(int deckId);
    Task<Card?> GetCardById(int id);
    Task<Card> CreateCard(Card newCard);
    Task<bool> UpdateCard(Card updatedCard, int id);
    Task<bool> DeleteCard(int id);
}