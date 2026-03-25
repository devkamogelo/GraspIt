using System.ComponentModel.DataAnnotations;

public class CardRequestDto
{
    [Required]
    public string? Front{get; set;}
    [Required]
    public string? Back{get; set;}
}