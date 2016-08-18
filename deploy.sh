#!/bin/bash
set -e
cd $(dirname $0)
cd public
git init
git add .
git commit -m "Deploy to GitHub Pages"
git push --force --quiet "git@github.com:timothypratley/where-are-you.git" master:gh-pages
rm -fr .git
cd ..
