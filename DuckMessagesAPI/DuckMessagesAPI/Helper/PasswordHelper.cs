using System.Security.Cryptography;

namespace DuckMessagesAPI.Helper
{
    public static class PasswordHelper
    {
        private const int SaltSize = 32; // 32 bytes for the salt
        private const int HashSize = 64; // 64 bytes for the hash
        private const int Iterations = 10000; // Number of iterations for PBKDF2

        /// <summary>
        /// Creates a password hash and salt from a given password string.
        /// </summary>
        /// <param name="password">The plain-text password to hash.</param>
        /// <param name="passwordHash">The generated password hash (output).</param>
        /// <param name="passwordSalt">The generated salt (output).</param>
        public static void CreatePasswordHash(string password, out byte[] passwordHash, out byte[] passwordSalt)
        {
            if (string.IsNullOrWhiteSpace(password))
            {
                throw new ArgumentException("Password cannot be empty or whitespace.", nameof(password));
            }

            // Generate a random salt
            passwordSalt = RandomNumberGenerator.GetBytes(SaltSize);

            // Create the hash using PBKDF2 algorithm
            using (var pbkdf2 = new Rfc2898DeriveBytes(password, passwordSalt, Iterations, HashAlgorithmName.SHA512))
            {
                passwordHash = pbkdf2.GetBytes(HashSize);
            }
        }

        /// <summary>
        /// Verifies a password against a stored hash and salt.
        /// </summary>
        /// <param name="password">The plain-text password to verify.</param>
        /// <param name="storedHash">The hash stored in the database.</param>
        /// <param name="storedSalt">The salt stored in the database.</param>
        /// <returns>True if the password is correct; otherwise, false.</returns>
        public static bool VerifyPasswordHash(string password, byte[] storedHash, byte[] storedSalt)
        {
            if (string.IsNullOrWhiteSpace(password))
            {
                throw new ArgumentException("Password cannot be empty or whitespace.", nameof(password));
            }
            if (storedHash.Length != HashSize)
            {
                throw new ArgumentException($"Invalid length of password hash ({storedHash.Length} bytes). Expected {HashSize} bytes.", nameof(storedHash));
            }
            if (storedSalt.Length != SaltSize)
            {
                throw new ArgumentException($"Invalid length of password salt ({storedSalt.Length} bytes). Expected {SaltSize} bytes.", nameof(storedSalt));
            }

            // Compute the hash of the input password using the stored salt
            using (var pbkdf2 = new Rfc2898DeriveBytes(password, storedSalt, Iterations, HashAlgorithmName.SHA512))
            {
                var computedHash = pbkdf2.GetBytes(HashSize);

                // Compare the computed hash with the stored hash in a way that prevents timing attacks
                return CryptographicOperations.FixedTimeEquals(computedHash, storedHash);
            }
        }
    }
}
