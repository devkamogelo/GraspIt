using Microsoft.AspNetCore.Mvc;

[ApiController]
[Route("api/[controller]")]
public class DecksController : ControllerBase
{
    private readonly IDeckRepository _deckRepository;

    public DecksController(IDeckRepository deckRepository)
    {
        _deckRepository = deckRepository;
    }

    [HttpGet]
    public async Task<IActionResult> GetAllDecks(){
        var decks = await _deckRepository.GetAllDecks();
        return Ok(decks.Select(deck => new DeckResponseDto
        {
            DeckId = deck.DeckId,
            Name = deck.Name, 
            Description = deck.Description
        }));
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetById(int id)
    {
        var deck = await _deckRepository.GetDeckById(id);
        return deck is null ? NotFound() : Ok(new DeckResponseDto
        {
            DeckId = deck.DeckId, 
            Name = deck.Name,
            Description = deck.Description,
        });
    }

    [HttpPost]
    public async Task<IActionResult> CreateDeck(DeckRequestDto request)
    {
        Deck deck = new Deck
        {
            Name = request.Name ?? string.Empty,
            Description = request.Description ?? string.Empty, 
        };
        await _deckRepository.CreateDeck(deck);
        DeckResponseDto response = new DeckResponseDto
        {
            Name = deck.Name,
            Description = deck.Description,
            DeckId = deck.DeckId
        };
        return CreatedAtAction(nameof(GetById), new{id = deck.DeckId}, response);
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> UpdateDeck(int id, DeckRequestDto request)
    {
        Deck updatedDeck = new Deck
        {
            Name = request.Name ?? string.Empty,
            Description = request.Description ?? string.Empty
        };
        var completed = await _deckRepository.UpdateDeck(id, updatedDeck);
        DeckResponseDto response = new DeckResponseDto
        {
            Name = updatedDeck.Name, 
            DeckId = id,
            Description = updatedDeck.Description
        };
        return !completed ? NotFound() : Ok(response);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteDeck(int id)
    {
        var completed = await _deckRepository.DeleteDeck(id);
        return !completed ? NotFound() : NoContent();
    }
}