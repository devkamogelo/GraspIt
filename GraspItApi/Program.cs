using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.dotnet ef migrations add InitialCreate
builder.Services.AddDbContext<AppDbContext>(options => 
options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));
builder.Services.AddControllers();
builder.Services.AddScoped<ICardRepository, CardRepository>();
builder.Services.AddScoped<IDeckRepository, DeckRepository>();
builder.Services.AddExceptionHandler<GlobalExceptionHandler>();
builder.Services.AddProblemDetails();

var app = builder.Build();


app.UseHttpsRedirection();
app.UseExceptionHandler();
app.MapControllers();

app.Run();