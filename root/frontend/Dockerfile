# Use an official Node.js runtime as the base image
FROM node:16-alpine

# Set the working directory in the container
WORKDIR /app

# Copy package.json and yarn.lock to the working directory
COPY package*.json yarn.lock ./

# Install dependencies using Yarn
RUN yarn install

# Copy the rest of the application code to the working directory
COPY . .

# Expose the port that the app runs on
EXPOSE 3000

# Command to run the application
CMD ["yarn", "start"]
