using System.ComponentModel.DataAnnotations;

public class DeckRequestDto
{
    [Required]
    [MaxLength(50)]
    public string? Name{get; set;}
    [Required]
    [MaxLength(100)]
    public string? Description{get; set;}
}