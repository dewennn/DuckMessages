using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using DuckMessagesAPI.Data.Models;

namespace DuckMessagesAPI.Data
{
    public partial class DuckMessagesContext : DbContext
    {
        public DuckMessagesContext()
        {
        }

        public DuckMessagesContext(DbContextOptions<DuckMessagesContext> options)
            : base(options)
        {
        }

        public virtual DbSet<TextMessage> TextMessages { get; set; } = null!;
        public virtual DbSet<User> Users { get; set; } = null!;
        public virtual DbSet<UserRelationship> UserRelationships { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseSqlServer("Server=Derren-Laptop;Database=DuckMessages;Trusted_Connection=True;TrustServerCertificate=True;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<TextMessage>(entity =>
            {
                entity.HasKey(e => e.MessageId)
                    .HasName("PK__TextMess__C87C037C509BD863");

                entity.Property(e => e.SentAt).HasDefaultValueSql("(getutcdate())");

                entity.HasOne(d => d.Receiver)
                    .WithMany(p => p.TextMessageReceivers)
                    .HasForeignKey(d => d.ReceiverId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Messages_Receiver");

                entity.HasOne(d => d.Sender)
                    .WithMany(p => p.TextMessageSenders)
                    .HasForeignKey(d => d.SenderId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Messages_Sender");
            });

            modelBuilder.Entity<User>(entity =>
            {
                entity.Property(e => e.UserId).HasDefaultValueSql("(newid())");

                entity.Property(e => e.CreatedAt).HasDefaultValueSql("(getutcdate())");

                entity.Property(e => e.PasswordHash).IsFixedLength();

                entity.Property(e => e.PasswordSalt).IsFixedLength();
            });

            modelBuilder.Entity<UserRelationship>(entity =>
            {
                entity.HasKey(e => e.RelationshipId)
                    .HasName("PK__UserRela__31FEB8613E461371");

                entity.Property(e => e.CreatedAt).HasDefaultValueSql("(getutcdate())");

                entity.HasOne(d => d.UserId1Navigation)
                    .WithMany(p => p.UserRelationshipUserId1Navigations)
                    .HasForeignKey(d => d.UserId1)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Relationships_User1");

                entity.HasOne(d => d.UserId2Navigation)
                    .WithMany(p => p.UserRelationshipUserId2Navigations)
                    .HasForeignKey(d => d.UserId2)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Relationships_User2");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
