using Microsoft.EntityFrameworkCore;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options): base(options)
    {
        
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Deck>()
            .HasMany(d => d.Cards)
            .WithOne(c => c.Deck)
            .HasForeignKey(c => c.DeckId)
            .OnDelete(DeleteBehavior.Cascade);
        modelBuilder.Entity<User>()
            .HasMany(u => u.Decks)
            .WithOne(d => d.User)
            .HasForeignKey(d => d.UserId)
            .OnDelete(DeleteBehavior.Cascade);
    }
    public DbSet<Card> Cards{get; set;}
    public DbSet<Deck> Decks{get; set;}
    public DbSet<User> Users{get; set;}
}