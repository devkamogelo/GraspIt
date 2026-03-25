using Microsoft.AspNetCore.Mvc;

[ApiController]
[Route("api/decks/{deckId}/[controller]")]
public class CardsController : ControllerBase
{
    private readonly ICardRepository _cardRepository;

    public CardsController(ICardRepository cardRepository)
    {
        _cardRepository = cardRepository;
    }

    [HttpGet()]
    public async Task<IActionResult> GetAllCards(int deckId)
    {
        var cards = await _cardRepository.GetAllCards(deckId);
        return Ok(cards.Select(card => new CardResponseDto
        {
            CardId = card.CardId,
            DeckId = card.DeckId,
            Front = card.Front,
            Back = card.Back
        }));
    }

    [HttpGet("{cardId}")]
    public async Task<IActionResult> GetCardById(int cardId)
    {
        var card = await _cardRepository.GetCardById(cardId);
        if (card is null)
        {
            return NotFound();
        }
        CardResponseDto response = new CardResponseDto
        {
            CardId = card.CardId,
            DeckId = card.DeckId,
            Front = card.Front,
            Back = card.Back
        };
        return Ok(response);
    }

    [HttpPost]
    public async Task<IActionResult> CreateCard(int deckId, CardRequestDto request)
    {
        Card newCard = new Card
        { 
            DeckId = deckId,
            Front = request.Front ?? string.Empty,
            Back = request.Back ?? string.Empty
        };
        var card = await _cardRepository.CreateCard(newCard);
        CardResponseDto response = new CardResponseDto
        {
            DeckId = card.DeckId,
            CardId = card.CardId,
            Front = card.Front,
            Back = card.Back
        };
        return CreatedAtAction(nameof(GetCardById), new {deckId = card.DeckId, cardId = card.CardId}, response);
    }
    
    [HttpPut("{cardId}")]
    public async Task<IActionResult> UpdateCard(int deckId, int cardId, CardRequestDto request)
    {
        Card updatedCard = new Card
        {
            Front = request.Front ?? string.Empty,
            Back = request.Back ?? string.Empty, 
        };
        var cardUpdated = await _cardRepository.UpdateCard(updatedCard, cardId);
        CardResponseDto response = new CardResponseDto
        {
            DeckId = deckId,
            CardId = updatedCard.CardId,
            Front = updatedCard.Front,
            Back = updatedCard.Back
        };
        return cardUpdated ? Ok(response) : NotFound();
    }

    [HttpDelete("{cardId}")]
    public async Task<IActionResult> DeleteCard(int cardId)
    {
        var cardDeleted = await _cardRepository.DeleteCard(cardId);
        return cardDeleted ? NoContent() : NotFound();
    }
}